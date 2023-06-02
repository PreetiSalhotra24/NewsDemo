package web.connect.newsdemo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import web.connect.newsdemo.databinding.FragmentAllNewsBinding
import web.connect.newsdemo.ui.adapters.AllNewsAdapter
import web.connect.newsdemo.ui.adapters.NewsEventAction
import web.connect.newsdemo.ui.viewmodel.AllNewsViewModel

class AllNewsFragment : Fragment() {

    private lateinit var binding: FragmentAllNewsBinding

    private val allNewsViewModel by lazy {
        ViewModelProvider(this)[AllNewsViewModel::class.java]
    }

    private val allNewsAdapter by lazy {
        AllNewsAdapter { news, action ->
            when (action) {
                NewsEventAction.ON_DELETE -> allNewsViewModel.deleteNews(news)
                NewsEventAction.ON_SHOW -> Toast.makeText(context, news.title, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allNewsViewModel.fetchHeadlinesNews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observer()
    }

    private fun observer() {
        allNewsViewModel.topHeadlinesNews.observe(viewLifecycleOwner) {
            allNewsAdapter.submitList(it.articles)
        }
    }

    private fun setAdapter() {
        binding.rvTopHeadlineNews.adapter = allNewsAdapter
    }

}