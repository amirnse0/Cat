package com.abbas.cats.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.request.ImageRequest
import coil3.compose.AsyncImage
import coil3.request.crossfade
import com.abbas.cats.R
import com.abbas.cats.usecase.Result
import com.abbas.cats.usecase.presentationmodel.Cat

@Composable
fun CatsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val viewModel = hiltViewModel<MainViewModel>()
    val data by viewModel.catsDataStateFlow.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()

    val reachedBottom: Boolean by remember {
        derivedStateOf { listState.reachedBottom() }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) viewModel.getCats()
    }

    when (data) {
        is Result.Success -> {
            CatsLazyColumn(
                cats = (data as Result.Success).data,
                listState = listState
            ) { it ->
                viewModel.clickOnCatItem(cat = it)
                navController.navigate("detail")
            }
        }

        Result.Loading -> {
            Box{
                if (!viewModel.isCacheEmpty()) {
                    CatsLazyColumn(
                        cats = viewModel.oldData,
                        listState = listState
                    ) { it ->
                        viewModel.clickOnCatItem(cat = it)
                        navController.navigate("detail")
                    }
                }
                LinearProgressIndicator(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .align(Alignment.BottomCenter)
                        .height(5.dp)
                        .width(400.dp)
                )
            }
        }

        else -> {
            Text(
                text = "Something wrong!",
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Composable
fun CatsLazyColumn(
    modifier: Modifier = Modifier,
    cats: List<Cat>,
    listState: LazyListState,
    onItemClick: (Cat) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {
        items(cats, key = { cat -> cat.id }) { cat ->
            CatItemCard(cat = cat) {
                onItemClick(cat)
            }
        }
    }
}

@Composable
fun CatItemCard(modifier: Modifier = Modifier, cat: Cat, onItemClick: () -> Unit) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .shadow(8.dp, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(16.dp),
        onClick = { onItemClick() }
    ) {
        CatPicture(image = cat.image)
        Text(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(10.dp),
            text = cat.name
        )
    }
}

@Composable
fun CatPicture(modifier: Modifier = Modifier, image: String, pictureSize: Int = 200) {
    val context = LocalContext.current

    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .size(pictureSize.dp),
        model = ImageRequest.Builder(context)
            .data(image)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

fun LazyListState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}