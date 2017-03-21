package com.wan7451.powerfulview.scroll;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 自定义ScrollView
 */
public class ZoomScrollView extends ScrollView {

    private View childView;// 孩子View

    private float initTouchY;// 首次点击的Y坐标

    private boolean shutTouch = false;// 是否关闭ScrollView的滑动.

    private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否需要动画.)

    private boolean isMoveing = false;// 是否开始移动.

    private View headerView;// 背景图控件.

    private int initTop, initBottom;// 初始高度


    private int zoomViewWidth;
    private int zoomViewHeight;


    // 状态：上部，下部，默认
    private enum State {
        UP, DOWN, NOMAL
    }

    // 默认状态
    private State state = State.NOMAL;

    /***
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public ZoomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /***
     * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
     * 方法，也应该调用父类的方法，使该方法得以执行.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }


//    // 滑动距离及坐标
//    private float xDistance, yDistance, xLast, yLast;
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                xDistance = yDistance = 0f;
//                xLast = ev.getX();
//                yLast = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                final float curX = ev.getX();
//                final float curY = ev.getY();
//
//                xDistance += Math.abs(curX - xLast);
//                yDistance += Math.abs(curY - yLast);
//                xLast = curX;
//                yLast = curY;
//
//                if(xDistance > yDistance){
//                    return false;
//                }
//        }
//
//        return super.onInterceptTouchEvent(ev);
//    }

    /**
     * touch 事件处理
     **/
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (childView != null) {

            commOnTouchEvent(ev);

            if (zoomViewWidth <= 0 || zoomViewHeight <= 0) {
                zoomViewWidth = headerView.getMeasuredWidth();
                zoomViewHeight = headerView.getMeasuredHeight();
            }
        }
        // ture：禁止控件本身的滑动.
        return shutTouch || super.onTouchEvent(ev);
    }

    /***
     * 触摸事件
     * @param ev
     */
    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                initTouchY = ev.getY();
                initTop = headerView.getTop();
                initBottom = headerView.getBottom();
                break;
            case MotionEvent.ACTION_UP:
                /** 回缩动画 **/
                if (isNeedAnimation()) {
                    animation();
                }

                if (getScrollY() == 0) {
                    state = State.NOMAL;
                }

                isMoveing = false;
                shutTouch = false;
                break;

            /***
             * 排除出第一次移动计算，因为第一次无法得知deltaY的高度， 然而我们也要进行初始化，就是第一次移动的时候让滑动距离归0.
             * 之后记录准确了就正常执行.
             */
            case MotionEvent.ACTION_MOVE:

                float touchY = ev.getY();
                float deltaY = touchY - initTouchY;// 滑动距离

                /** 对于首次Touch操作要判断方位：UP OR DOWN **/
                if (deltaY < 0) {
                    state = State.UP;
                } else if (deltaY > 0) {
                    state = State.DOWN;
                }

                if (state == State.UP) {
                    deltaY = deltaY < 0 ? deltaY : 0;
                    isMoveing = false;
                    shutTouch = false;
                } else if (state == State.DOWN) {
                    if (getScrollY() <= deltaY) {
                        shutTouch = true;
                        isMoveing = true;
                    }
                    deltaY = deltaY < 0 ? 0 : deltaY;
                }

                if (isMoveing) {
                    // 初始化头部矩形
                    if (normal.isEmpty()) {
                        // 保存正常的布局位置
                        normal.set(childView.getLeft(), childView.getTop(),
                                childView.getRight(), childView.getBottom());
                    }
                    // 移动布局(手势移动的1/3)
                    float inner_move_H = deltaY / 2;

                    childView.layout(normal.left, (int) (normal.top + inner_move_H),
                            normal.right, (int) (normal.bottom + inner_move_H));

                    /** image_bg **/
                    float image_move_H = deltaY / 2;
                    int current_Top = (int) (initTop + image_move_H);
                    int current_Bottom = (int) (initBottom + image_move_H);

                    int currHeight = (int) (current_Bottom - current_Top + image_move_H);
                    int currWidth = (int) (currHeight * 1.0f / zoomViewHeight * zoomViewWidth);

                    ViewGroup.LayoutParams layoutParams = headerView.getLayoutParams();
                    layoutParams.width = currWidth;
                    layoutParams.height = currHeight;

                    headerView.setLayoutParams(layoutParams);
                }
                break;

            default:
                break;

        }
    }

    /***
     * 回缩动画
     */
    public void animation() {


        final ViewGroup.LayoutParams lp = headerView.getLayoutParams();
        final float w = headerView.getLayoutParams().width;// 图片当前宽度
        final float h = headerView.getLayoutParams().height;// 图片当前高度
        final float newW = zoomViewWidth;// 图片原宽度
        final float newH = zoomViewHeight;// 图片原高度

        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(0.0F, 1.0F);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                lp.width = (int) (w - (w - newW) * cVal);
                lp.height = (int) (h - (h - newH) * cVal);
                headerView.setLayoutParams(lp);
            }
        });
        anim.start();


        // 开启移动动画
        TranslateAnimation inner_Anim = new TranslateAnimation(0, 0,
                childView.getTop(), normal.top);
        inner_Anim.setDuration(100);
        childView.startAnimation(inner_Anim);
        childView.layout(normal.left, normal.top, normal.right, normal.bottom);


        normal.setEmpty();

    }

    /**
     * 是否需要开启动画
     **/
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }


    // 注入背景图
    public void setHeaderView(View view) {
        this.headerView = view;
    }

}
