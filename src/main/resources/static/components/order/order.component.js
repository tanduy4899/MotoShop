angular.module('myProduct').controller('OrderController',OrderController );
OrderController.$inject=['ProductService','$log'];
function OrderController(ProductService,$log) {
    var vm = this;
    vm.sendData = function (isValid) {
        ProductService.postOrder(vm.getData).then(function (response) {
            $log.log(response);
        },function (response) {
            vm.error = response;
        });
        if(isValid){
            alert('đăng kí thành công');
        }
    };


    $log.log(vm);
}