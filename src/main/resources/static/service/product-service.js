angular
    .module('myProduct')
    .factory('ProductService', ProductService);
ProductService.$inject = ['$http'];
function ProductService($http) {
    var vm = this;
    vm.dataProduct = '/api/products/';
    vm.dataCategory = '/api/categories/';
    vm.dataGetOrder = '/api/orders';
    return {
        // begin product index
        getAllProducts: getAllProducts,
        getById: getById,
        getAllSlide: getAllSlide,
        getCategory: getCategory,
        postOrder: postOrder,
    };
    <!-- trang index-->
    function getAllProducts(filter) {
        var _SEARCH_URL = '/api/products?page=' + (filter.page - 1) + '&size=' + filter.size;

        if (filter.search && filter.search.length) {
            // _SEARCH_URL = _SEARCH_URL + '&name=' + filter.search + '&fistPrice=' + filter.search + '&lastPrice=' + filter.search;
            _SEARCH_URL = _SEARCH_URL + '&name=' + filter.search ;
        }
        if (filter.categoryId) {
            _SEARCH_URL += '&id=' + filter.categoryId
        }

        return $http.get(_SEARCH_URL).then(function (resp) {
            return resp.data;
        });
    }

    function getById(id) {
        return $http.get(vm.dataProduct + id);
    }

    function getAllSlide() {
        return $http.get('slide/slide.json');
    }

    function getCategory() {
        return $http.get(vm.dataCategory);
    }

    function postOrder(getData) {
        return $http.post(vm.dataGetOrder, getData)
    }

    <!-- begin get Save-Product -->
    function getSaveProduct() {
        return $http.get(vm.dataProduct);
    }
}
