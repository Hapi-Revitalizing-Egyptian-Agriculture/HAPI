// Generated by Dagger (https://dagger.dev).
package com.example.hapi.presentation.settings.landfarmers;

import com.example.hapi.domain.usecase.landowner.FetchFarmersUseCase;
import com.example.hapi.domain.usecase.landowner.GetFarmersUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class FarmersListViewModel_Factory implements Factory<LandFarmersViewModel> {
  private final Provider<FetchFarmersUseCase> fetchFarmersUseCaseProvider;

  private final Provider<GetFarmersUseCase> getFarmersUseCaseProvider;

  public FarmersListViewModel_Factory(Provider<FetchFarmersUseCase> fetchFarmersUseCaseProvider,
      Provider<GetFarmersUseCase> getFarmersUseCaseProvider) {
    this.fetchFarmersUseCaseProvider = fetchFarmersUseCaseProvider;
    this.getFarmersUseCaseProvider = getFarmersUseCaseProvider;
  }

  @Override
  public LandFarmersViewModel get() {
    return newInstance(fetchFarmersUseCaseProvider.get(), getFarmersUseCaseProvider.get());
  }

  public static FarmersListViewModel_Factory create(
      Provider<FetchFarmersUseCase> fetchFarmersUseCaseProvider,
      Provider<GetFarmersUseCase> getFarmersUseCaseProvider) {
    return new FarmersListViewModel_Factory(fetchFarmersUseCaseProvider, getFarmersUseCaseProvider);
  }

  public static LandFarmersViewModel newInstance(FetchFarmersUseCase fetchFarmersUseCase,
                                                 GetFarmersUseCase getFarmersUseCase) {
    return new LandFarmersViewModel(fetchFarmersUseCase, getFarmersUseCase);
  }
}
