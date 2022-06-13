package com.dicoding.picodiploma.sigfood.UI.container.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.sigfood.UI.adapter.ItemAdapter
import com.dicoding.picodiploma.sigfood.UI.adapter.RecipeAdapter
import com.dicoding.picodiploma.sigfood.UI.category.CategoryActivity
import com.dicoding.picodiploma.sigfood.databinding.FragmentSearchBinding
import com.dicoding.picodiploma.sigfood.model.DetailRecipe
import com.dicoding.picodiploma.sigfood.model.SearchRecipe

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel by viewModels<SearchViewModel>()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeAdapter = RecipeAdapter(ArrayList<SearchRecipe>())

        searchViewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading(it)
        }

        searchViewModel.getSearchRecipe().observe(viewLifecycleOwner) {
            if (it != null) {
                recipeAdapter.setList(it)
                isLoading(false)
            }
        }
        binding.ayam.setOnClickListener {
            val intent = Intent(requireContext(), CategoryActivity::class.java)
            intent.putExtra(CategoryActivity.EXTRA_CATEGORY, "resep-ayam")
            startActivity(intent)
        }

        binding.sayur.setOnClickListener {
            val intent = Intent(requireContext(), CategoryActivity::class.java)
            intent.putExtra(CategoryActivity.EXTRA_CATEGORY, "resep-sayuran")
            startActivity(intent)
        }

        binding.apply {
            rvRecipe.layoutManager = LinearLayoutManager(requireContext())
            rvRecipe.setHasFixedSize(true)
            rvRecipe.adapter = recipeAdapter

            searchMenu.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query?.isEmpty() == true) {
                        return false
                    }
                    searchRecipe()
                    searchMenu.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun searchRecipe(){
        binding.apply {
            val query = searchMenu.query.toString()
            searchViewModel.setSearchRecipe(query)
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