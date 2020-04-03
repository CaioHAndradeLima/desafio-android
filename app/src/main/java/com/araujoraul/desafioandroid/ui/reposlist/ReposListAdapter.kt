package com.araujoraul.desafioandroid.ui.reposlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.Repositories
import com.araujoraul.desafioandroid.data.model.Repository
import com.araujoraul.desafioandroid.utils.loadImage
import com.araujoraul.desafioandroid.utils.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_repos_list.view.*

class ReposListAdapter(val clickListener: ItemRepoClickListener) :
       PagedListAdapter<Repository, ReposListAdapter.ReposListViewHolder>(DIFF_CALLBACK)
{

    private var repository: Repositories? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReposListViewHolder.create(parent, clickListener)

    override fun onBindViewHolder(holder: ReposListViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    class ReposListViewHolder(itemView: View, val clickListener: ItemRepoClickListener):
            RecyclerView.ViewHolder(itemView){

        fun bind(item: Repository?){
            item?.let {

                with(itemView){
                    item_title_repo.text = item.name
                    item_desc_repo.text = item.description
                    item_number_pull_request.text = item.forksCount.toString()
                    item_number_stars.text = item.stargazersCount.toString()
                    item_username.text = item.owner.login

                    item_image_profile.loadImage(item.owner.avatarUrl)

                    itemView.setSafeOnClickListener {
                        clickListener.onClick(item)
                    }
                }
            }
        }

        companion object{
            fun create(parent: ViewGroup, clickListener: ItemRepoClickListener) = ReposListViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_repos_list,
                            parent,
                            false
                    ),
                    clickListener
            )
        }

    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repository>(){
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem.id == newItem.id

        }
    }

    interface ItemRepoClickListener{
        fun onClick(item: Repository)
    }

    fun setRepositories(repositories: Repositories?){
        this.repository = repositories
        notifyDataSetChanged()
    }

}

