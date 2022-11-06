package casa.falconer.toys.ui.main

import android.annotation.TargetApi
import android.content.Context
import android.os.Build.VERSION_CODES
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import casa.falconer.toys.R
import casa.falconer.toys.models.Animal




class AnimalsAdapter(var dataset: List<Animal>,  val onItemClick: ((Animal) -> Unit)?) :
    RecyclerView.Adapter<AnimalsAdapter.AnimalViewHolder>() {


    inner class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageButton: AppCompatButton = view.findViewById(R.id.animalButton)

        init {
            imageButton.setOnClickListener {
                onItemClick?.invoke(dataset[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = dataset[position]
        holder.imageButton.background =
            ContextCompat.getDrawable(holder.imageButton.context, animal.image)
        holder.imageButton.contentDescription = animal.name
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}