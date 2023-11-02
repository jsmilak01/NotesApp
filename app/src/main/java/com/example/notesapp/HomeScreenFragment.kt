package com.example.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeScreenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    //parameter needed: list of strings of note titles to be displayed in recycler view
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)

        //load data from room library into the recycler view
        //val dataset = data from library
        //val customAdapter = CustomAdapter(dataset)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        //recyclerView.adapter = customAdapter
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.layoutManager = staggeredGridLayoutManager
        val newNoteButton = view.findViewById<ImageButton>(R.id.add_note_button)
        val userButton = view.findViewById<ImageButton>(R.id.login_button)

        newNoteButton.setOnClickListener {
            //change screen to a new(empty) note fragment
            val newNoteFragment = NoteFragment.newInstance("", "")
            this.parentFragmentManager.commit{
                replace(R.id.fragment_container_view, newNoteFragment)
            }
        }
        //need code when clicking item in recycler view opening up note fragment with that data

        userButton.setOnClickListener{
            //open login page
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeScreenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

class CustomAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titleTextView: TextView
        val descriptionTextView: TextView
        init{
            titleTextView = view.findViewById<TextView>(R.id.recycler_note_title)
            descriptionTextView = view.findViewById<TextView>(R.id.recycler_note_description)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_view_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CustomAdapter.ViewHolder, position: Int) {
        viewHolder.titleTextView.text = dataSet[position]
        viewHolder.descriptionTextView.text = dataSet[position]
    }

    override fun getItemCount(): Int {
        return this.dataSet.size
    }

}