package com.araujoraul.desafioandroid.ui.repodetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.PullRequests
import com.araujoraul.desafioandroid.utils.loadImage
import com.araujoraul.desafioandroid.utils.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_repo_detail.view.*


class RepoDetailAdapter(val clickListener: ItemRepoDetailClickListener):
        RecyclerView.Adapter<RepoDetailAdapter.RepoDetailViewHolder>(){

    var items = ArrayList<PullRequests>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoDetailViewHolder = RepoDetailViewHolder.create(parent, clickListener)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RepoDetailViewHolder, position: Int) {
      val item = items[position]

        holder.bind(item)

    }


    class RepoDetailViewHolder(itemView: View,val clickClickListener: ItemRepoDetailClickListener): RecyclerView.ViewHolder(itemView){
       fun bind(item: PullRequests?){
           item?.let {

               with(itemView){
                   item_title_pull_request.text = item.title
                   item_body_pull_request.text = item.body
                   item_username.text = item.user.login
                   item_first_last_name.text = item.createdAt.apply {}.toString()

                   item_image_profile.loadImage(item.user.avatarUrl)

                   itemView.setSafeOnClickListener {
        clickClickListener.onClick(item)
                   }
               }

           }


       }

        companion object{
            fun create(parent: ViewGroup, clickListener: ItemRepoDetailClickListener) = RepoDetailViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_repo_detail,
                            parent,
                            false
                    ),
                    clickListener
            )
        }

    }

    interface ItemRepoDetailClickListener{
        fun onClick(item: PullRequests)
    }

}