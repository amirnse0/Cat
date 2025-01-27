package com.abbas.cats.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun CatDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val cat by viewModel.catSelectedItemStateFlow.collectAsStateWithLifecycle()

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
            CatPicture(image = cat!!.image)
            Text(text = cat!!.name)
            Text(text = cat!!.description)
            Text(text = "Origin: ${cat!!.origin}")
            Text(text = "Life span: ${cat!!.lifeSpan}")
            Text(text = "Temperament: ${cat!!.temperament}")
        }
    }
}