package com.gabriel.jaya.emprendimiento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gabriel.jaya.emprendimiento.databinding.FragmentProfileBinding
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profilePagerAdapter: ProfilePagerAdapter

    override fun onCreateView(inflater: LayoutInflater, c: ViewGroup?, b: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, c, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profilePagerAdapter = ProfilePagerAdapter(this)
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

// Adaptador para el ViewPager
class ProfilePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        // Devuelve un nuevo fragment para cada tab
        // Por simplicidad, podemos usar el mismo fragment de publicaciones para el ejemplo
        // En una app real, crearías ProfileParksFragment, ProfileTreesFragment, etc.
        return when (position) {
            1 -> ProfilePostsFragment() // El que se ve en la imagen
            else -> PlaceholderFragment.newInstance(position) // Fragments de ejemplo
        }
    }
}