package com.araujoraul.desafioandroid.presentation.ui.reposlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.Repository
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_repos_list.view.*

class ReposListAdapter(val clickListener: RepoClickListener): ListAdapter<Repository, ReposListAdapter.ReposListViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposListViewHolder {

        return ReposListViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repos_list, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: ReposListViewHolder, position: Int) {

        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }

    class ReposListViewHolder(itemView: View, val clickListener: RepoClickListener) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.txtName)
        private val description: TextView = itemView.findViewById(R.id.txtDescription)
        private val stars: TextView = itemView.findViewById(R.id.stars)
        private val forks: TextView = itemView.findViewById(R.id.forks)
        private val _avatar: CircleImageView = itemView.findViewById(R.id.avatar)
        private val _username: TextView = itemView.findViewById(R.id.username)
        private var repo: Repository? = null

        fun bind(repo: Repository?) {
            repo?.let {
                    name.text = repo.name
                    description.text = repo.description
                    forks.text = repo.forks.toString()
                    stars.text = repo.stars.toString()

//                    _username.text = repo.owner.login
//                    _avatar.loadImage(repo.owner.avatarUrl)

                itemView.setOnClickListener {
                    clickListener.onClick(repo)
                }

            }

            if (repo != null){
                showRepoData(repo)
            }

        }

        private fun showRepoData(repo: Repository) {
            this.repo = repo

            name.text = repo.name


            // if the description is missing, hide the TextView
            var descriptionVisibility = View.GONE
            if (repo.description != null) {
                description.text = repo.description
                descriptionVisibility = View.VISIBLE
            }
            description.visibility = descriptionVisibility

            stars.text = repo.stars.toString()
            forks.text = repo.forks.toString()

        }

    }

    companion object {

        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem == newItem
        }
    }


}


