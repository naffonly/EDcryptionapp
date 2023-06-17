package org.d3if0012.edcryptionapp.ui.article

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if0012.edcryptionapp.MainActivity
import org.d3if0012.edcryptionapp.databinding.FragmentArticleBinding
import org.d3if0012.edcryptionapp.network.ApiStatus

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

        viewModel.getStatus().observe(viewLifecycleOwner){
            updateProgress(it)
        }

        viewModel.scheduleUpdater(requireActivity().application)

    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestNotificationPermission()
                }

            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                MainActivity.PERMISSION_REQUEST_CODE
            )
        }
    }



}