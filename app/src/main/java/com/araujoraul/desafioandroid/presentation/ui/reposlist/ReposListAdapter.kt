package com.araujoraul.desafioandroid.presentation.ui.reposlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.Repository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repos_list.view.*

class ReposListAdapter : ListAdapter<Repository, ReposListAdapter.ReposListViewHolder>(REPO_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposListViewHolder {

        return ReposListViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repos_list, parent, false))
    }
    override fun onBindViewHolder(holder: ReposListViewHolder, position: Int) {

       val item = getItem(position)

        if (item != null){
           holder.bind(item)
        }
    }

    class ReposListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private var repo: Repository? = null

        fun bind(repo: Repository?){

            repo?.let {

                with(itemView){
                    txtName.text = repo.name
                    txtDescription.text = repo.description
                    forks.text = repo.forks.toString()
                    stars.text = repo.stars.toString()
//                    username.text = repo.owner.login
//
//                    avatar.loadImage(repo.owner.avatarUrl)
                }
            }
        }

        private fun showRepoData() {
          TODO( "Not implemented yet")
        }

    }

    companion object{

        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem == newItem
        }
    }

}

private fun ImageView.loadImage(imageUrl: String?){
    Glide.with(this.context)
            .load(imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(this)
}

