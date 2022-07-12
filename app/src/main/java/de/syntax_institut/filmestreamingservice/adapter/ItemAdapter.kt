package de.syntax_institut.filmestreamingservice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax_institut.filmestreamingservice.R
import de.syntax_institut.filmestreamingservice.data.model.Movie
import de.syntax_institut.filmestreamingservice.ui.HomeFragment
import de.syntax_institut.filmestreamingservice.ui.HomeFragmentDirections

/**
 * Der Item Adapter weist den views im ViewHolder den Inhalt zu
 */
class ItemAdapter(
    private val context: Context,
    private val dataset: List<Movie>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_itemMovieTitle)
        val imageView: ImageView = view.findViewById(R.id.iv_itemMovieImage)
    }

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        // das itemLayout wird gebaut
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        // und in einem ViewHolder zurückgegeben
        return ItemViewHolder(itemLayout)
    }

    /**
     * hier findet der Recyclingprozess statt
     * die vom ViewHolder bereitgestellten Parameter erhalten die Information des Listeneintrags
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataset[position]

        holder.textView.text = context.resources.getString(item.stringResource)
        holder.imageView.setImageResource(item.imageResource)

        // TODO: bei Klick auf die ImageView soll zum DetailFragment navigiert werden
                holder.itemView.setOnClickListener {
                    holder.itemView.findNavController()
                        .navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                            titleId = item.stringResource,
                            imageId = item.imageResource,
                            screenId = item.screenImageResource,
                        )
                    )

                }
    }

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
    override fun getItemCount(): Int {
        return dataset.size
    }
}
