
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caffaine2222.*
import network.MarsPhoto
import java.util.concurrent.Executors

class MarsPhotosAdapter(private var mList: List<MarsPhoto>) : RecyclerView.Adapter<MarsPhotosAdapter.ViewHolder>() {

    lateinit var v:View

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_rv_item, parent, false)
            v = view
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val executor = Executors.newSingleThreadExecutor()
        val ItemsViewModel = mList.get(position)

        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemsViewModel.img)
        // sets the text to the textview from our itemHolder class
        holder.title.text = ItemsViewModel.id
        holder.description.text = ItemsViewModel.img_src
        executor.execute{
            try {
                val img = java.net.URL(ItemsViewModel.img_src).openStream()
                var bitmap:Bitmap = BitmapFactory.decodeStream(img)
                holder.imageView.setImageBitmap(bitmap)





            }catch (e: java.lang.Exception){
//                var builder = NotificationCompat.Builder(v.context, "115492")
//                    .setSmallIcon(R.drawable.mac)
//                    .setContentTitle("Error")
//                    .setContentText("can't Download photo : ${ItemsViewModel.id}")
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//                NotificationManagerCompat.from(v.context).notify(1674852, builder.build())
            }
        }
//        holder.element.setOnClickListener{
//            if (RvActivity.switch == 0){
//                setData(ItemsViewModel.meals)
//                RvActivity.switch = 1
//                RvActivity.choosen_res_img.setImageResource(ItemsViewModel.img)
//                RvActivity.choosen_res_title.text = ItemsViewModel.title
//                RvActivity.choosen_res_card.visibility = View.VISIBLE
//            }
//        }

    }

    fun setData(m: List<MarsPhoto>){
        mList = m

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