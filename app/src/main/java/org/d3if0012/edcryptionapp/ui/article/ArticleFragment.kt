package org.d3if0012.edcryptionapp.ui.article

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if0012.edcryptionapp.R
import org.d3if0012.edcryptionapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {


    private lateinit var  binding: FragmentArticleBinding
    private lateinit var  myAdapter: ArticleAdapter
    private val viewModel: ArticleViewModel by lazy {
        ViewModelProvider(this)[ArticleViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        myAdapter = ArticleAdapter()
        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context,RecyclerView.VERTICAL))
            adapter  = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner){
            myAdapter.updateData(it)
        }

//        viewModel.getStatus().observe(viewLifecycleOwner){
//            updateProgress(it)
//        }
    }

//    private fun updateProgress(status: ApiStatus) {
//        when (status) {
//            ApiStatus.LOADING -> {
//                binding.progressBar.visibility = View.VISIBLE
//            }
//            ApiStatus.SUCCESS -> {
//                binding.progressBar.visibility = View.GONE
//            }
//            ApiStatus.FAILED -> {
//                binding.progressBar.visibility = View.GONE
//                binding.networkError.visibility = View.VISIBLE
//            }
//        }
//    }

}