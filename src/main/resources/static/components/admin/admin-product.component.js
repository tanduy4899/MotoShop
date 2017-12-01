angular.module('myAdmin').controller('AdminController', AdminController);

AdminController.$inject = ['$log', 'adminService', '$state', '$stateParams'];

function AdminController($log, adminService, $state, $stateParams) {
    var vm = this;
    vm.error = "";
    vm.filter = {
        search: $stateParams.search || '',
        page: $stateParams.page || 1,
        size: $stateParams.size || 10
    };
    vm.search = search;
    vm.pageChange = pageChange;
    getData(vm.filter);

    function search() {
        vm.filter.page = 1;
        getData(vm.filter);
    }

    function pageChange() {
        getData(vm.filter);
    }

    function getData(filter) {
        vm.users = [];
        $log.info(filter);
        $state.go($state.current, filter, {notify: false, reload: false, location: 'replace'});
        vm.error = "";
        adminService.getUsers(filter).then(function (resp) {
            vm.users = resp;
            $log.info(vm.users);
            vm.filter.totalItems = resp.total;
        }, function error(errResp) {
            vm.error = "Error loading data";
        });
    }

    vm.delete = function (id) {
        adminService.deleteRegisters(id).then(function () {
            getData(vm.filter);
            alert('xoa thanh cong ');

        }, function (response) {
            vm.error = response.data;
        });
    };

}
