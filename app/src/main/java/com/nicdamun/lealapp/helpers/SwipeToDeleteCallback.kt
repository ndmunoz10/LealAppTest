package com.nicdamun.lealapp.helpers

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.nicdamun.lealapp.R
import com.nicdamun.lealapp.ui.home.adapters.MainContentAdapter

class SwipeToDeleteCallback(
    private val mainContentAdapter: MainContentAdapter,
    context: Context
): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    private var icon: Drawable? = null
    private var background: ColorDrawable

    init {
        icon = ContextCompat.getDrawable(context, R.drawable.ic_delete)
        background = ColorDrawable(Color.RED)
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        val iconMargin = (itemView.height - (icon?.intrinsicHeight ?: 0)) / 2
        val iconTop = itemView.top + (itemView.height - (icon?.intrinsicHeight ?: 0)) / 2
        val iconBottom = iconTop + (icon?.intrinsicHeight ?: 0)
        val backgroundCornerOffset = 20
        when {
            dX > 0 -> {
                val iconLeft = itemView.left + iconMargin + (icon?.intrinsicWidth ?: 0)
                val iconRight = itemView.left + iconMargin
                icon?.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                background.setBounds(itemView.left, itemView.top, itemView.left + dX.toInt() + backgroundCornerOffset, itemView.bottom)
            }
            dX < 0 -> {
                val iconLeft = itemView.right - iconMargin - (icon?.intrinsicWidth ?: 0)
                val iconRight = itemView.right - iconMargin
                icon?.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                background.setBounds(itemView.right + dX.toInt() - backgroundCornerOffset, itemView.top, itemView.right, itemView.bottom)
            }
            else -> background.setBounds(0, 0, 0, 0)
        }
        background.draw(c)
        icon?.draw(c)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        mainContentAdapter.deleteItem(position)
    }
}