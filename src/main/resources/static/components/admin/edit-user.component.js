angular.module('myAdmin').controller('EditController', EditController);

EditController.$inject = ['$log', 'adminService', '$state', '$stateParams'];

function EditController($log, adminService, $state, $stateParams) {
    var vm = this;

    adminService.getByIdRegister($stateParams.id).then(function (response) {
        vm.postData = response.data;
    },function (response) {
        vm.error = response.data;
    });
    vm.editRegister= function () {
        adminService.editRegisters($stateParams.id,vm.postData).then(function (){
            alert('sua thanh cong ');
            $state.go("admin");
        }, function (response) {
            vm.error = response.data;
        });
    };


}
