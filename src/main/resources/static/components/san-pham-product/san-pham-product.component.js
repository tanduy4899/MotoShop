angular.module('myProduct').controller('SanPhamProductController',SanPhamProductController);
SanPhamProductController.$inject = ['ProductService','$log','$stateParams'];

function SanPhamProductController(ProductService, $log,$stateParams) {
    var vm = this;

    ProductService.getAllProducts().then(function (response) {
        vm.getSanPham = response.data;
        // var i, j, text;
        // vm.A= [];
        // for(i =0; i<4 ; i++){
        //     text =  vm.products[i];
        //     vm.A.push(text);
        // }

    }, function (response) {
        vm.error = response.data;
    });
}
