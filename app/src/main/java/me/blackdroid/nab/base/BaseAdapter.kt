package me.blackdroid.nab.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import me.blackdroid.nab.BR

open class BaseAdapter<M>(
        private val layoutId: Int = 0,
        dataList: List<M>
) : RecyclerView.Adapter<BaseAdapter.ViewHolder<M>>() {

    protected val dataset = mutableListOf<M>()

    init {
        dataset.addAll(dataList)
    }

    protected val _itemClickEvent: PublishSubject<M> = PublishSubject.create()

    val itemClickEvent: Observable<M>
        get() = _itemClickEvent

    var data: List<M> = emptyList()
        set(values) {
            dataset.addAll(values)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<M> =
            ViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            viewType,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: ViewHolder<M>, position: Int) {
        holder.run {
            bind(dataset[position])
            itemView.setOnClickListener { _itemClickEvent.onNext(dataset[position]) }
        }
    }

    override fun getItemCount(): Int = dataset.size

    override fun getItemViewType(position: Int): Int = layoutId

    class ViewHolder<M>(
            val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: M) {
            binding.run {
                setVariable(BR.model, item)
                executePendingBindings()
            }
        }
    }
}
