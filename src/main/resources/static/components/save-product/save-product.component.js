angular.module("myProduct").controller("SaveProductController",SaveProductController);
SaveProductController.inject =['ProductService','$log',];
function SaveProductController(ProductService,$log) {

    var vm = this;

    vm.totalItems = 69;
    vm.currentPage = 4;
    vm.maxSize = 9;
    vm.pageChanged = function() {
        ProductService.getPage(vm.currentPage,vm.maxSize).then(function (response) {
            vm.trang = response.data;
        },function (response) {
            vm.error = response;
        });
        $log.log('Page changed to: ' + vm.currentPage);
    };
    ProductService.getPage(vm.currentPage,vm.maxSize).then(function (response) {
        vm.trang = response.data;
    },function (response) {
        vm.error = response;
    });
    $log.log(vm);
}
