package com.araujoraul.desafioandroid.presentation.ui.reposlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.Repository
import com.araujoraul.desafioandroid.presentation.ui.reposlist.RepoClickListener

class ReposListAdapter(val clickListener: RepoClickListener): ListAdapter<Repository, ReposListViewHolder>(REPO_COMPARATOR) {

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


    companion object {

        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository) = oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository) = oldItem == newItem
        }
    }


}


