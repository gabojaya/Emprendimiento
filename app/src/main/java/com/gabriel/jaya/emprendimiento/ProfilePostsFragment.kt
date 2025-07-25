package com.gabriel.jaya.emprendimiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.jaya.emprendimiento.PostAdapter
import com.gabriel.jaya.emprendimiento.databinding.FragmentProfilePostsBinding
import com.gabriel.jaya.emprendimiento.Post

class ProfilePostsFragment : Fragment() {
    private var _binding: FragmentProfilePostsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, c: ViewGroup?, b: Bundle?): View {
        _binding = FragmentProfilePostsBinding.inflate(inflater, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userPosts = listOf(
            Post( "Monica", R.drawable.avatar_monica, "3 min ago", "Quito",
                "Cuidemos nuestros espacios verdes.", R.drawable.arbol_frutal, 21, 4)
        )
        binding.rvProfilePosts.layoutManager = LinearLayoutManager(context)
        binding.rvProfilePosts.adapter = PostAdapter(userPosts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}