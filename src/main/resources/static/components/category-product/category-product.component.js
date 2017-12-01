angular.module('myProduct').controller('CategoryProductController', CategoryProductController);
CategoryProductController.inject = ['ProductService', '$log', '$stateParams', '$state'];
function CategoryProductController(ProductService, $log, $stateParams, $state) {
    var vm = this;
    ProductService.getCategory().then(function (response) {
        vm.getCategories = response.data;
    }, function (response) {
        vm.error = response;
    });

}