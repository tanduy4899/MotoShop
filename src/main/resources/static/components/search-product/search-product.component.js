angular.module('myProduct').controller('SearchProductController', SearchProductController);

SearchProductController.$inject = ['ProductService', '$log', '$stateParams'];

function SearchProductController(ProductService, $log, $stateParams) {
    var vm = this;
    vm.dynamicPopover = {
        content: ' input name',
        title: ' input email'
    };

    ProductService.getAllProducts().then(function (response) {
        vm.getAllProducts = response.data;
    }, function (response) {
        vm.error = response.data;
    });

    vm.slider = {
        minValue: 0,
        maxValue: 1000,
        options: {
            floor: 0,
            ceil: 1000,
            translate: function (value, sliderId, label) {
                switch (label) {
                    case 'model':
                        return value;
                    case 'high':
                        return value;
                    default:
                        return '$' + value
                }
            }
        }
    };

    vm.findName = '';

    ProductService.getCategory().then(function (response) {
        vm.category = response.data;
    }, function (response) {
        vm.error = response.data;
    });

    vm.changedValue=function(item){
        console.log(item);
        vm.Selected= item.id;
    };
    vm.Search = function () {
        ProductService.getFindAll(vm.slider.minValue, vm.slider.maxValue, vm.findName,vm.Selected).then(function (response) {
            vm.findAll = response.data;
        }, function (response) {
            vm.error = response.data;
        });
    };

    vm.Selected = '';


    $log.log(vm)
}