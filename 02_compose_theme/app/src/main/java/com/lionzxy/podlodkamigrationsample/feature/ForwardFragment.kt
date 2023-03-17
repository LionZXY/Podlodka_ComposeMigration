package com.lionzxy.podlodkamigrationsample.feature

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.lionzxy.podlodkamigrationsample.utils.BackButtonListener
import com.lionzxy.podlodkamigrationsample.utils.ComposeFragment
import com.lionzxy.podlodkamigrationsample.utils.requireRouter

class ForwardFragment : ComposeFragment(), BackButtonListener {
    private val viewModel: ForwardViewModel by viewModels {
        ForwardViewModelFactory(
            containerName = requireArguments().getString(EXTRA_NAME)!!,
            number = requireArguments().getInt(EXTRA_NUMBER),
            router = requireRouter()
        )
    }

    override fun onBackPressed(): Boolean {
        viewModel.onBack()
        return true
    }

    @Composable
    override fun RenderView() {
        ComposableForward(
            forwardState = viewModel.forwardState,
            onForward = viewModel::onForward,
            onFullscreen = viewModel::onForwardFullScreen,
            onBack = viewModel::onBack
        )
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