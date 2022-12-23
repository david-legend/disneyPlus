import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.davidcobbina.disneyplus.R
import com.davidcobbina.disneyplus.data.local.model.Menu
import com.davidcobbina.disneyplus.layout.WindowInfo
import com.davidcobbina.disneyplus.layout.rememberWindowInfo
import com.davidcobbina.disneyplus.ui.components.*
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.davidcobbina.disneyplus.data.remote.api.ApiConstants
import com.davidcobbina.disneyplus.navigation.Screen
import com.davidcobbina.disneyplus.ui.components.shimmers.GridShimmerItem
import com.davidcobbina.disneyplus.ui.screens.list_movies_screen.ListMoviesViewModel


//TODO:: Add Padding around close button
//TODO:: Figure out passing variable to viewModel
//TODO:: Is there a lazyFlowRow ??

@Composable
fun ListMoviesScreen(
    navController: NavHostController,
    menu: Menu,
    viewModel: ListMoviesViewModel = hiltViewModel()
) {
    val movies by viewModel.moviesFeed.collectAsState()
    val isMoviesLoading by viewModel.moviesFeedLoading.collectAsState()

    val paddingSpacing = dimensionResource(id = R.dimen.spacingMd)
    val windowInfo = rememberWindowInfo()
    val spacing = 10.dp
    val containerPadding = 8
    val screenWidth = windowInfo.screenWidth - (containerPadding + (spacing.value * 2))
    val itemWidth: Dp = if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact)
        (screenWidth / 3).dp else (screenWidth / (6)).dp
    val itemHeight: Dp = itemWidth + (itemWidth / 2)

    Box(
        modifier = Modifier
            .padding(
                vertical = paddingSpacing,
            )
            .fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.topSpacingMd)))
                } else {
                    Spacer(modifier = Modifier.height(paddingSpacing))
                }

                if (menu.isBrand) {
                    Image(
                        painter = painterResource(id = menu.icon),
                        colorFilter = menu.color?.let { ColorFilter.tint(color = it) },
                        contentDescription = menu.title,
                    )
                } else {
                    TextWithIcon(
                        title = menu.title,
                        hasIcon = false,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(paddingSpacing))
                if (isMoviesLoading) {
                    GridShimmerItem(rowItemCount = 3, gridItems = 12)
                } else {

                    FlowRow(
                        mainAxisSpacing = spacing,
                        mainAxisSize = SizeMode.Expand,
                        crossAxisSpacing = spacing
                    ) {
                        for (movie in movies) {
                            MovieItem(
                                url = movie.posterPath,
                                contentDescription = movie.getMovieTitle(),
                                width = itemWidth,
                                height = itemHeight,
                                modifier = Modifier
                                    .padding(bottom = dimensionResource(id = R.dimen.paddingExtraMedium))
                                    .clickable {
                                        if (movie.mediaType == ApiConstants.MEDIA_TYPE_TV) {
                                            navController.navigate(
                                                route = Screen.TvSeriesDetailScreen.passTvSeries(
                                                    movie
                                                )
                                            )
                                        } else {
                                            navController.navigate(
                                                route = Screen.MovieDetailScreen.passMovie(movie)
                                            )
                                        }

                                    }
                            )
                        }

                    }
                }

            }


        }
        CircularIconButton(
            child = {
                CustomIcon(
                    contentDescription = stringResource(R.string.close_button),
                    iconColor = Color.White,
                    icon = Icons.Filled.Close,
                )
            },
            buttonColor = MaterialTheme.colorScheme.surface,
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(vertical = paddingSpacing)
                .clickable {
                    //pop current screen off
                }
        )
    }

}

