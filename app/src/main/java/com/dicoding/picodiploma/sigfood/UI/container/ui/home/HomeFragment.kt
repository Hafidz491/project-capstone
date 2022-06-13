package com.dicoding.picodiploma.sigfood.UI.container.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sigfood.UI.adapter.ItemAdapter
import com.dicoding.picodiploma.sigfood.UI.detail.DetailActivity
import com.dicoding.picodiploma.sigfood.databinding.FragmentHomeBinding
import com.dicoding.picodiploma.sigfood.model.DetailRecipe
import com.dicoding.picodiploma.sigfood.model.Recipes
import com.dicoding.picodiploma.sigfood.response.RecipesResponse

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getListRecipe()
        itemAdapter = ItemAdapter(ArrayList<Recipes>())

        homeViewModel.listRecipe.observe(viewLifecycleOwner) {
            setAdapter(it)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading(it)
        }

        itemAdapter.setOnClickCallback(object : ItemAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DetailRecipe) {
                Intent(context, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_THUMB, data.thumbnail)
                    it.putExtra(DetailActivity.EXTRA_TITLE, data.name)
                    it.putExtra(DetailActivity.EXTRA_USER, data.author.author)
                    it.putExtra(DetailActivity.EXTRA_TIMES, data.times)
                    it.putExtra(DetailActivity.EXTRA_DIFFICULTY, data.dificulty)
                    it.putExtra(DetailActivity.EXTRA_INGREDIENT, data.ingredients.size)
                    it.putExtra(DetailActivity.EXTRA_STEP, data.step.size)
                    startActivity(it)
                }
            }

        })

    }


    private fun setAdapter(listRecipe: RecipesResponse) {

        val item = ArrayList<Recipes>()

        for(i in listRecipe.results.indices) {
            item.add(listRecipe.results[i])
        }

        val adapter = ItemAdapter(item)
        binding.apply {
            rvRecipe.layoutManager = LinearLayoutManager(requireContext())
            rvRecipe.setHasFixedSize(true)
            rvRecipe.adapter = adapter
        }
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading){
            binding.pgMain.visibility = View.VISIBLE
            binding.rvRecipe.visibility = View.GONE
        }else{
            binding.pgMain.visibility = View.GONE
            binding.rvRecipe.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

