package com.volkanhotur.basemvvm.android.ui.view.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.volkanhotur.basemvvm.R
import timber.log.Timber
import java.util.*

/**
 * @author volkanhotur
 */
class ViewPagerIndicator @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : RadioGroup(context, attrs) {

    private var mViewPager: ViewPager? = null

    private var mItemDividerWidth = 0

    private var mButtonDrawable: Drawable? = null

    private var mItemRadius = 0

    private var mItemSelectedColor = 0

    private var mItemUnselectedColor = 0

    /**
     * Create a StateListDrawable for the pager indicator
     *
     * @return a proper StateListDrawable
     */
    private val selectorDrawable: StateListDrawable?
        get() {
            var d: StateListDrawable? = null

            try {
                d = StateListDrawable()

                val selectedDrawable = ShapeDrawable(OvalShape())
                selectedDrawable.paint.color = mItemSelectedColor
                selectedDrawable.intrinsicHeight = mItemRadius * 2
                selectedDrawable.intrinsicWidth = mItemRadius * 2

                val unselectedDrawable = ShapeDrawable(OvalShape())
                unselectedDrawable.paint.color = mItemUnselectedColor
                unselectedDrawable.intrinsicHeight = mItemRadius * 2
                unselectedDrawable.intrinsicWidth = mItemRadius * 2

                d.addState(intArrayOf(android.R.attr.state_checked), selectedDrawable)
                d.addState(intArrayOf(), unselectedDrawable)
            } catch (e: Exception) {
                Timber.tag(TAG).e(getMessageFor(e))
            }
            return d
        }

    /**
     * Initialize ViewPagerIndicator with a properly set up ViewPager
     * @param viewPager an instance of android.support.v4.view.ViewPager
     * @throws IllegalStateException if no adapter has been provided to the viewPager
     */
    @Throws(IllegalStateException::class)
    fun initWithViewPager(viewPager: ViewPager?) {
        if (viewPager == null) {
            return
        }

        checkNotNull(viewPager.adapter) { "ViewPager has no adapter set." }
        try {
            mViewPager = viewPager
            mViewPager?.addOnPageChangeListener(mOnPageChangeListener)
            addViews()
        } catch (e: Exception) {
            Timber.tag(TAG).e(getMessageFor(e))
        }
    }

    /**
     * Add page indicators based on the attached ViewPager
     */
    private fun addViews() {
        try {
            if (mViewPager == null || mViewPager?.adapter == null || mViewPager?.adapter?.count == 0) {
                return
            }

            removeAllViews()

            val firstItem = AppCompatRadioButton(context)
            firstItem.text = ""
            firstItem.buttonDrawable = mButtonDrawable?.constantState?.newDrawable()

            var params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            firstItem.layoutParams = params
            firstItem.isClickable = false

            addView(firstItem)

            mViewPager?.let {
                for (i in 1 until it.adapter!!.count) {
                    val item = AppCompatRadioButton(context)
                    item.text = ""
                    item.buttonDrawable = mButtonDrawable?.constantState?.newDrawable()

                    params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                    params.setMargins(mItemDividerWidth, 0, 0, 0)

                    item.layoutParams = params
                    item.isClickable = false

                    addView(item)
                }
            }
            check(firstItem.id)
        } catch (e: Exception) {
            Timber.tag(TAG).e(getMessageFor(e))
        }
    }

    private val mOnPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            try {
                this@ViewPagerIndicator.check(getChildAt(position).id)
            } catch (e: Exception) {
                Timber.tag(TAG).e(getMessageFor(e))
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    /**
     * Always get a message for an exception
     * @param e an Exception
     * @return a String describing the Exception
     */
    private fun getMessageFor(e: Exception?): String? {
        if (e == null) {
            return "$TAG: No Message."
        }

        return if (e.message != null) e.message else e.javaClass.name + ": No Message."
    }

    companion object {
        private const val TAG = "ViewPagerIndicator"
    }
    /**
     * Constructor with attributes
     * @param context a Context
     * @param attrs an Attribute set
     */
    /**
     * Default Constructor
     * @param context a Context
     */
    init {
        try {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER

            val a = context.theme.obtainStyledAttributes(attrs,
                    R.styleable.ViewPagerIndicator, 0, 0)
            mItemRadius = resources.getDimensionPixelSize(R.dimen.default_item_radius)
            mItemRadius = a.getDimensionPixelSize(R.styleable.ViewPagerIndicator_itemRadius, mItemRadius)
            mItemDividerWidth = a.getDimensionPixelSize(R.styleable.ViewPagerIndicator_itemDividerWidth, resources.getDimensionPixelSize(R.dimen.default_item_divider_width))

            val theme = a.getInt(R.styleable.ViewPagerIndicator_defaultIndicatorTheme, 0)

            if (theme == 0) {
                mItemSelectedColor = ContextCompat.getColor(getContext(), R.color.default_indicator_on)
                mItemUnselectedColor = ContextCompat.getColor(getContext(), R.color.default_indicator_off)
            } else {
                mItemSelectedColor = ContextCompat.getColor(getContext(), R.color.default_indicator_light_on)
                mItemUnselectedColor = ContextCompat.getColor(getContext(), R.color.default_indicator_light_off)
            }

            mItemSelectedColor = a.getColor(R.styleable.ViewPagerIndicator_itemSelectedColor, mItemSelectedColor)
            mItemUnselectedColor = a.getColor(R.styleable.ViewPagerIndicator_itemUnselectedColor, mItemUnselectedColor)
            mButtonDrawable = selectorDrawable

            val drawableResId = a.getResourceId(R.styleable.ViewPagerIndicator_pagerIndicatorDrawable, 0)

            if (drawableResId != 0) {
                mButtonDrawable = ContextCompat.getDrawable(getContext(), drawableResId)
            }
        } catch (e: Exception) {
            Timber.tag(TAG).e(getMessageFor(e))
        }
    }
}