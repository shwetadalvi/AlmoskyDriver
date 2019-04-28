package com.almosky.driver.interfaces;

import com.almosky.driver.models.Remarks;

import java.util.List;


public interface ClickListeners {
    interface CategoryItemEvents<T> {
        void onClickedEdit(T items);

        void onDeletedItem(T items);
    }

    interface getRemarkList {
        void onClickedRemarkItem(List<Remarks> remarksList);
    }
   /* interface ItemTimeClick<T> {
        void onClickedTimeItem(T item);
    }*/
}
