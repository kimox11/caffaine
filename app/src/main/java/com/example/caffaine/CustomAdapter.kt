
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caffaine.*

class CustomAdapter(private var mList: ArrayList<restaurant>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {



    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_rv_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList.get(position)

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.img)

        // sets the text to the textview from our itemHolder class
        holder.title.text = ItemsViewModel.title
        holder.description.text = ItemsViewModel.description
        holder.element.setOnClickListener{
            if (RvActivity.switch == 0){
                setData(ItemsViewModel.meals)
                RvActivity.switch = 1
                RvActivity.choosen_res_img.setImageResource(ItemsViewModel.img)
                RvActivity.choosen_res_title.text = ItemsViewModel.title
                RvActivity.choosen_res_card.visibility = View.VISIBLE
            }else{

            }
        }

    }

    fun setData(m: ArrayList<restaurant>){
        mList = m
        notifyDataSetChanged()
        RvActivity.recyclerview.scrollToPosition(0)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val title: TextView = itemView.findViewById(R.id.res_menu_title)
        val description: TextView = itemView.findViewById(R.id.res_menu_description)
        val element:androidx.cardview.widget.CardView = itemView.findViewById(R.id.res_card)

    }
}