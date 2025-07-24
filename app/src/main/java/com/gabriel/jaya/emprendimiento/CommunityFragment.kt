package com.gabriel.jaya.emprendimiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.jaya.emprendimiento.PostAdapter
import com.gabriel.jaya.emprendimiento.databinding.FragmentCommunityBinding
import com.gabriel.jaya.emprendimiento.Post

class CommunityFragment : Fragment() {

    private var _binding: FragmentCommunityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, c: ViewGroup?, b: Bundle?): View {
        _binding = FragmentCommunityBinding.inflate(inflater, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Datos de ejemplo
        val dummyPosts = listOf(
            Post(
                "Monica", "url_avatar_monica", "3 min ago", "Quito",
                "Cuidemos nuestros espacios verdes.", "url_imagen_basura", 21, 4
            ),
            Post(
                "Daniel", "url_avatar_daniel", "2 hrs ago", "Quito",
                "\"Cada pequeña acción cuenta: cuida hoy el planeta y estarás construyendo el futuro que quieres ver.\"",
                null, 6, 18
            )
        )

        binding.rvPosts.layoutManager = LinearLayoutManager(context)
        binding.rvPosts.adapter = PostAdapter(dummyPosts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}