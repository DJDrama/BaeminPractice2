package com.dj.baeminpractice2.presentation.ui.intro.terms.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dj.baeminpractice2.R
import com.dj.baeminpractice2.databinding.ItemLayoutTermTitleBinding
import com.dj.baeminpractice2.presentation.model.TermPresentationModel

class TermsTitleAdapter
constructor(
    private val onClickTermTitle: (TermPresentationModel) -> Unit
) : ListAdapter<TermPresentationModel, TermsTitleAdapter.TermsTitleViewHolder>(TermTitleDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermsTitleViewHolder {
        return TermsTitleViewHolder(
            binding = ItemLayoutTermTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickTermTitle = onClickTermTitle
        )
    }

    override fun onBindViewHolder(holder: TermsTitleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TermsTitleViewHolder(
        private val binding: ItemLayoutTermTitleBinding,
        private val onClickTermTitle: (TermPresentationModel) -> Unit
    ) : ViewHolder(binding.root) {
        private var term: TermPresentationModel? = null

        init {
            binding.root.setOnClickListener {
                term?.let {
                    onClickTermTitle(it)
                }
            }
        }

        fun bind(term: TermPresentationModel) {
            this.term = term
            binding.tvTermTitle.text = term.title
            binding.tvTermTitle.setTextColor(
                ResourcesCompat.getColor(
                    binding.root.resources,
                    if (term.isSelected)
                        R.color.baemin_color
                    else R.color.dark_gray,
                    binding.root.context.theme
                )
            )
        }
    }

    object TermTitleDiffCallBack : ItemCallback<TermPresentationModel>() {
        override fun areItemsTheSame(
            oldItem: TermPresentationModel,
            newItem: TermPresentationModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TermPresentationModel,
            newItem: TermPresentationModel
        ): Boolean {
            return oldItem == newItem
        }

    }

}
