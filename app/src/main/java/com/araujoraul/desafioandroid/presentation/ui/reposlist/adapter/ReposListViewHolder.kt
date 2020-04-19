package com.araujoraul.desafioandroid.presentation.ui.reposlist.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.Repository
import com.araujoraul.desafioandroid.extension.loadImage
import com.araujoraul.desafioandroid.presentation.ui.reposlist.RepoClickListener
import de.hdodenhof.circleimageview.CircleImageView

class ReposListViewHolder(itemView: View, private val clickListener: RepoClickListener) : RecyclerView.ViewHolder(itemView) {
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

            _username.text = repo.owner.username
            _avatar.loadImage(repo.owner.avatarUrl)

            itemView.setOnClickListener {
                clickListener.onClick(repo)
            }

        }

        if (repo != null) {
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
