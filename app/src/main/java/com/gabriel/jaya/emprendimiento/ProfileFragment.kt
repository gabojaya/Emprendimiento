package com.gabriel.jaya.emprendimiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gabriel.jaya.emprendimiento.databinding.FragmentProfileBinding
import com.gabriel.jaya.emprendimiento.databinding.FragmentProfilePostsBinding
import com.google.android.material.tabs.TabLayoutMediator

// --- CLASE PRINCIPAL DEL PERFIL ---
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, c: ViewGroup?, b: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profilePagerAdapter = ProfilePagerAdapter(this)
        binding.viewPager.adapter = profilePagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Parques"
                1 -> "Publicaciones"
                2 -> "Árboles"
                3 -> "Me gusta"
                else -> null
            }
        }.attach()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

// --- ADAPTADOR CORREGIDO ---
class ProfilePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        // AHORA SÍ DEVOLVEMOS FRAGMENTS REALES
        return when (position) {
            1 -> ProfilePostsFragment() // Tu fragment de publicaciones
            else -> PlaceholderFragment.newInstance(position) // Un fragment genérico para las otras tabs
        }
    }
}

// --- FRAGMENT GENÉRICO (Añade esta clase al final del archivo o en uno nuevo) ---
class PlaceholderFragment : Fragment() {
    // Puedes mejorar esto para que muestre un texto diferente según la pestaña
    override fun onCreateView(inflater: LayoutInflater, c: ViewGroup?, b: Bundle?): View {
        // Por ahora, solo devolvemos una vista vacía para que no crashee.
        // En una app real, aquí irían los RecyclerViews de Parques, Árboles, etc.
        return View(context)
    }

    companion object {
        fun newInstance(position: Int): PlaceholderFragment {
            return PlaceholderFragment()
        }
    }
}