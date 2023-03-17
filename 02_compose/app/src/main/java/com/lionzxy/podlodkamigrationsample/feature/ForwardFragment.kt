package com.lionzxy.podlodkamigrationsample.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lionzxy.podlodkamigrationsample.utils.BackButtonListener
import com.lionzxy.podlodkamigrationsample.databinding.FragmentForwardBinding
import com.lionzxy.podlodkamigrationsample.utils.requireRouter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * Created by terrakok 26.11.16
 */
class ForwardFragment : MvpAppCompatFragment(), BackButtonListener, ForwardView {
    private lateinit var binding: FragmentForwardBinding


    private val containerName
        get() = requireArguments().getString(EXTRA_NAME)!!
    private val presenter by moxyPresenter {
        ForwardPresenter(
            container = containerName,
            number = requireArguments().getInt(EXTRA_NUMBER),
            router = requireRouter()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForwardBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.toolbar.title = containerName
        binding.toolbar.setNavigationOnClickListener {
            presenter.onBack()
        }
        binding.forwardButton.setOnClickListener {
            presenter.onForward()
        }
        binding.fullscreen.setOnClickListener {
            presenter.onForwardFullScreen()
        }
    }

    override fun onChainSetUp(chain: String) {
        binding.chainText.text = chain
    }


    override fun onBackPressed(): Boolean {
        presenter.onBack()
        return true
    }

    companion object {
        private const val EXTRA_NAME = "extra_name"
        private const val EXTRA_NUMBER = "extra_number"

        fun getNewInstance(name: String?, number: Int): ForwardFragment {
            return ForwardFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                    putInt(EXTRA_NUMBER, number)
                }
            }
        }
    }
}