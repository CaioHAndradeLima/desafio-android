package com.araujoraul.desafioandroid.presentation.ui.reposlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.Repository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repos_list.view.*

class ReposListAdapter(var list: List<Repository>) :
       RecyclerView.Adapter<ReposListAdapter.ReposListViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposListViewHolder {
        return ReposListViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repos_list, parent, false))
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ReposListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ReposListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(item: Repository?){
            item?.let {

                with(itemView){
                    txtName.text = item.name
                    txtDescription.text = item.description
                    forks.text = item.forksCount.toString()
                    stars.text = item.stargazersCount.toString()
                    username.text = item.owner.login

                    avatar.loadImage(item.owner.avatarUrl)
                }
            }
        }
    }


}

private fun ImageView.loadImage(imageUrl: String?){
    Glide.with(this.context)
            .load(imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(this)
}

