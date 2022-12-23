
package com.example.sftassignment.di
import com.example.sftassignment.data.network.ApiService
import com.example.sftassignment.data.repositories.HomePageRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideHomePageRepo(apiService: ApiService): HomePageRepo {
    return HomePageRepo(apiService)
  }
}
