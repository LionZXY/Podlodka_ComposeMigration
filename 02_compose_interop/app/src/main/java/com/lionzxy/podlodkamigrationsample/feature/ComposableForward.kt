package com.lionzxy.podlodkamigrationsample.feature

import android.widget.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.lionzxy.podlodkamigrationsample.R

@Composable
fun ComposableForward(
    forwardState: ForwardState,
    onForward: () -> Unit = {},
    onFullscreen: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            AppBar(
                title = forwardState.title,
                onBack = onBack
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = forwardState.chain)
            Button(onClick = onForward) {
                Text(stringResource(R.string.forward_main_btn))
            }
            AndroidView(
                factory = { context ->
                    Button(context).apply {
                        text = context.getString(R.string.forward_fullscreen_btn)
                        setOnClickListener {
                            onFullscreen()
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun AppBar(
    title: String,
    onBack: () -> Unit
) = TopAppBar(
    contentPadding = PaddingValues(horizontal = 8.dp)
) {
    Icon(
        modifier = Modifier.clickable(
            indication = rememberRipple(),
            interactionSource = remember { MutableInteractionSource() },
            onClick = onBack
        ),
        painter = painterResource(
            R.drawable.ic_arrow_back_black_24dp
        ),
        contentDescription = null
    )
    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = title
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun ComposableForwardPreview() {
    ComposableForward(
        forwardState = ForwardState(
            title = "Test",
            chain = "[0] -> [1]"
        ),
    )
}