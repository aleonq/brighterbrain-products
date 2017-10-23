package com.mindworks.shoppingraga.productlisting;

import com.mindworks.shoppingraga.datastore.beans.Product;
import com.mindworks.shoppingraga.productlisting.mvp.ProductListModel;
import com.mindworks.shoppingraga.productlisting.mvp.ProductListMvp;
import com.mindworks.shoppingraga.productlisting.mvp.ProductListPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by taru on 10/22/2017.
 */
public class MainActivityPresenterTest {

    private ProductListMvp.Presenter presenter;

    @Mock
    public ProductListMvp.View view;

    @Mock
    public ProductListMvp.Model model;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setupPresenterForTesting() {
//      view = mock(ProductListMvp.View.class);
//      model = mock(ProductListModel.class);
//      OR
//      MockitoAnnotations.initMocks(this);
        presenter = new ProductListPresenter(model);
        presenter.setView(view);
    }

    @Test
    public void loadData() throws Exception{
        ArrayList<Product> list = new ArrayList<>();
        when(model.getProductsList()).thenReturn(list);
        presenter.loadData();
        verify(model).getProductsList();
        verify(view).displayProducts(list);
    }

    @Test
    public void onProductClicked() throws Exception {
        presenter.onProductClicked(null);
       verify(view).launchProductDetailsActivity();
    }

    @Test
    public void onFabClicked() throws Exception {
        presenter.onFabClicked();
        verify(view).launchProductDetailsActivity();
    }

    @Test
    public void onOptionsSettingsSelected() throws Exception {
        presenter.onOptionsSettingsSelected();
        verify(view).showLocaliationSettings();
    }
}