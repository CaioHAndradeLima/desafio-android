package com.araujoraul.desafioandroid.presentation.ui.pullrequests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.araujoraul.desafioandroid.R
import com.araujoraul.desafioandroid.data.model.PullRequest
import com.araujoraul.desafioandroid.util.loadImage
import de.hdodenhof.circleimageview.CircleImageView


class PullRequestAdapter(val pulls: List<PullRequest>) : RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false))
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val item = pulls[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = pulls.count()

    class PullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.txtTitle)
        private val body: TextView = itemView.findViewById(R.id.txtBody)
        private val _avatar: CircleImageView = itemView.findViewById(R.id.avatar)
        private val _username: TextView = itemView.findViewById(R.id.username)
        private val _date: TextView = itemView.findViewById(R.id.date)
        private var pull: PullRequest? = null

        fun bind(pull: PullRequest?) {

            pull?.let {

                    title.text = pull.title
                    body.text = pull.body
//                    _date.text = pull.createdAt.toString()
//                    _username.text = pull.user.login
//                    _avatar.loadImage(pull.user.avatarUrl)
            }

                if (pull != null){
                    showPullData(pull)
                }


        }


        private fun showPullData(pull: PullRequest) {
            this.pull = pull

            title.text = pull.title

            // if the body is missing, hide the TextView
            var bodyVisibility = View.GONE
            if (pull.body != null) {
                body.text = pull.body
                bodyVisibility = View.VISIBLE
            }
            body.visibility = bodyVisibility

//            _date.text = pull.createdAt.toString()
//            _username.text = pull.user.login
//            _avatar.loadImage(pull.user.avatarUrl)
        }

    }


}