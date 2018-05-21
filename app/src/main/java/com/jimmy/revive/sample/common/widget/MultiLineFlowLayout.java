package com.jimmy.revive.sample.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * 支持限制行数FlowLayout, 扩展于cn.mucang.android.selectcity.ui
 * <p>
 * Created by Jimmy on 2017/5/20.
 */
public class MultiLineFlowLayout extends ViewGroup {

    //记录每行的高度
    private SparseIntArray lineHeightArray = new SparseIntArray();
    //每个子view之间的间距
    private int lineSpacing = 10;
    //每一行之间的间距
    private int columnSpacing = 10;
    private int maxLineNumber = -1; // == -1无限制

    public MultiLineFlowLayout(Context context) {
        super(context);
    }

    public MultiLineFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        lineHeightArray.clear();

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //记录view在当前行中的第几列
        int columnIndex = 0;
        //记录当前在计算第几行
        int currentLineNumber = 0;
        //当前根据每个子view计算出的总宽
        int measuredWidth = 0;
        //当前根据每个子view计算出的总高度
        int measuredHeight = 0;
        int currentLineWidth = 0;
        int currentLineHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (currentLineNumber == maxLineNumber) {
                child.setVisibility(View.GONE);
                continue;
            }
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            //如果子view有margin属性，则更新view的宽度和高度的值
            int leftMargin = 0;
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                leftMargin = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                childHeight += ((ViewGroup.MarginLayoutParams) layoutParams).topMargin
                        + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            }
            //如果是第一列的view，则忽略左边距
            if (columnIndex != 0) {
                childWidth += Math.max(leftMargin, columnSpacing);
            }
            //将view的高度加上lineSpace的值
            childHeight += lineSpacing;
            ////先判断是否超出了最大宽度，如果单个子view的宽度就超过最大宽度，则忽略之，如果child为不可见状态，则忽略之，
            if (child.getVisibility() == GONE || childWidth > width) {
                childHeight = 0;
                childWidth = 0;
                child.setVisibility(GONE);
            }
            if (currentLineWidth + childWidth > width) {
                lineHeightArray.put(currentLineNumber, currentLineHeight);
                //换行了就先将本行的信息保存，再重置columnIndex和其他项的值
                measuredHeight += currentLineHeight;
                measuredWidth = Math.max(measuredWidth, currentLineWidth);
                // 如果当前行==最大限制行数，需要减去lineSpacing
                if (currentLineNumber == maxLineNumber) {
                    measuredHeight -= lineSpacing;
                    child.setVisibility(View.GONE);
                    continue;
                }
                //因为换行了，则需要将当前子view的宽度减去左边距
                if (columnIndex != 0) {
                    childWidth -= Math.max(leftMargin, columnSpacing);
                }
                columnIndex = 0;
                currentLineHeight = childHeight;
                currentLineWidth = childWidth;
                currentLineNumber++;
            } else {
                currentLineWidth += childWidth;
                currentLineHeight = Math.max(currentLineHeight, childHeight);
            }
            //如果是最后一个view
            if (i == getChildCount() - 1) {
                //最后一行是不需要linespace的，所以要减去lineSpacing的高度
                currentLineHeight -= lineSpacing;
                measuredHeight += currentLineHeight;
                measuredWidth = Math.max(measuredWidth, currentLineWidth);
                lineHeightArray.put(currentLineNumber, currentLineHeight);
            }
            //给当前view设置保存位置信息的tag，这里应该避免无限的生成新对象
            MultiLineFlowLayout.ViewTag tag = (MultiLineFlowLayout.ViewTag) child
                    .getTag();
            if (tag == null) {
                child.setTag(new MultiLineFlowLayout.ViewTag(currentLineNumber, columnIndex++));
            } else {
                tag.columnIndex = columnIndex++;
                tag.lineIndex = currentLineNumber;
            }
        }
        width = widthMode == View.MeasureSpec.EXACTLY ? width : measuredWidth;
        height = heightMode == View.MeasureSpec.EXACTLY ? height : measuredHeight;
        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (lineHeightArray == null || lineHeightArray.size() == 0) {
            return;
        }
        int currentLine = 0;
        int currentLineHeight = lineHeightArray.get(currentLine);
        int leftPostion = 0;
        int topPostion = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            MultiLineFlowLayout.ViewTag viewTag = (MultiLineFlowLayout.ViewTag) view.getTag();
            if (viewTag != null && view.getVisibility() != GONE) {
                int line = viewTag.lineIndex;
                //如果此时换行显示了，则要更新leftPostion和topPostion的值
                if (line > currentLine) {
                    topPostion += currentLineHeight;
                    currentLine = line;
                    currentLineHeight = lineHeightArray.get(currentLine);
                    leftPostion = 0;
                }
                int left = leftPostion;
                int top = topPostion;
                int right;
                int bottom;
                int leftMargin = 0;
                int topMargin = 0;
                //计算左边的开始坐标
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    leftMargin = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                    topMargin = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                }
                if (viewTag.columnIndex != 0) {
                    left += Math.max(leftMargin, columnSpacing);
                }
                right = left + view.getMeasuredWidth();
                //继续计算顶部的坐标，为了界面好看，默认是居中显示的，如果view设置了topMargin，则取margin和自动居中的最大值，这里需要用高度减去linespace的值
                if (currentLineHeight - lineSpacing > view.getMeasuredHeight()) {
                    //先算出如果居中显示所需要的offset
                    int topOffset = (currentLineHeight - lineSpacing - view.getMeasuredHeight()) / 2;
                    topOffset = Math.max(topOffset, topMargin);
                    top += topOffset;
                }
                bottom = top + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);
                leftPostion = right;
            }
        }
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    public void setMaxLineNumber(int maxLineNumber) {
        this.maxLineNumber = maxLineNumber > 0 ? maxLineNumber : -1;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public void setColumnSpacing(int columnSpacing) {
        this.columnSpacing = columnSpacing;
    }

    private static class ViewTag {

        public ViewTag(int lineIndex, int columnIndex) {
            this.lineIndex = lineIndex;
            this.columnIndex = columnIndex;
        }

        int lineIndex;
        int columnIndex;
    }

}
