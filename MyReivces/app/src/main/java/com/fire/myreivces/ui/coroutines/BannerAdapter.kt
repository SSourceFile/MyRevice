package com.fire.myreivces.ui.coroutines

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.fire.myreivces.R
import com.fire.myreivces.http.UserItem
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

class BannerAdapter: BaseBannerAdapter<UserItem>() {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner
    }

    override fun bindData(
        holder: BaseViewHolder<UserItem>?,
        data: UserItem?,
        position: Int,
        pageSize: Int
    ) {
//        holder?.setImageResource(R.id.banner_img, data?.imagePath)
        var img = holder?.findViewById<ImageView>(R.id.banner_img)
        Glide.with(img?.context!!).load(data?.imagePath).into(img)

    }
}