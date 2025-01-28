package com.abbas.cats.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abbas.cats.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun CatDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val cat by viewModel.catSelectedItemStateFlow.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    if (cat == null) {
        Text(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            text = "Nothing to show"
        )
    } else {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Box {
                CatPicture(image = cat!!.image, pictureSize = 400)
                LikeIcon(
                    modifier = modifier
                        .align(Alignment.BottomEnd),
                    id = cat!!.id,
                    viewModel = viewModel,
                    coroutineScope = coroutineScope
                )
            }
            val textModifier = modifier.padding(top = 5.dp)
            Text(
                modifier = textModifier,
                text = cat!!.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(modifier = textModifier, text = cat!!.description)
            Text(modifier = textModifier, text = "Origin: ${cat!!.origin}")
            Text(modifier = textModifier, text = "Life span: ${cat!!.lifeSpan}")
            Text(modifier = textModifier, text = "Temperament: ${cat!!.temperament}")
        }
    }
}

@Composable
fun LikeIcon(modifier: Modifier = Modifier, id: String, viewModel: MainViewModel, coroutineScope: CoroutineScope) {
    val isFavorite by viewModel.isFavoriteStateFlow.collectAsStateWithLifecycle()
    val likeImage =
        if (isFavorite) painterResource(R.drawable.ic_like) else painterResource(R.drawable.ic_dislike)
    IconButton(
        modifier = modifier,
        onClick = {
            coroutineScope.launch {
                viewModel.toggleFavorite(id, !isFavorite)
            }
        },
    ) {
        Image(
            modifier = modifier
                .padding(5.dp),
            painter = likeImage,
            contentDescription = null
        )
    }
}