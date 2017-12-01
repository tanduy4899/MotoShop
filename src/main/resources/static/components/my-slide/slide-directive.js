angular.module('myProduct').directive('mySlide', function () {
    return {
        restrict: 'E',
        templateUrl: 'components/my-slide/my-slide.template.html',
        controller: function (ProductService, $log) {
            var vm = this;
            vm.active = 0;
            vm.myInterval = 3000;
            ProductService.getAllSlide().then(function (response) {
                vm.slides = response.data;
            }, function (response) {
                vm.error = response.data;
            });
            $log.log(vm)
        },
        controllerAs: '$ctrl'
    }
});
