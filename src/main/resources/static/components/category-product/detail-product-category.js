angular.module('myProduct').controller('DetailCategoryProductController', DetailCategoryProductController);
DetailCategoryProductController.inject = ['ProductService', '$log', '$stateParams', '$state'];
function DetailCategoryProductController(ProductService, $log, $stateParams, $state) {
    var vm = this;
    vm.error = "";
    vm.filter = {
        search: $stateParams.search || '',
        page: $stateParams.page || 1,
        size: $stateParams.size || 9,
        categoryId: $stateParams.id
    };
    vm.pageChange = pageChange;
    getData(vm.filter);
    function pageChange() {
        getData(vm.filter);
    }
    function getData(filter) {
        $log.info(filter);
        $state.go($state.current, filter, {notify: false, reload: false, location: 'replace'});
        vm.error = "";
        ProductService.getAllProducts(vm.filter).then(function (response) {
            vm.getIdCategory = response;
            vm.filter.totalItems = response.total;
            $log.info(vm.filter.totalItems);
        }, function (response) {
            vm.error = "Error loading data";
        });
    }

    $log.log(vm);

}