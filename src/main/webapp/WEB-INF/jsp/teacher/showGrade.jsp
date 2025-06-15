<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>课程信息显示</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入bootstrap -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/xlsx.full.min.js"></script>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>

</head>
<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
	<div class="row">
		<jsp:include page="menu.jsp"></jsp:include>
		<div class="col-md-10">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<h1 class="col-md-5">已选该课程学生名单</h1>
						<form class="bs-example bs-example-form col-md-5 form-inline" role="form"
							  style="margin: 20px 0 10px 0;"
							  action="${pageContext.request.contextPath}/teacher/selectGrade"
							  id="form1" method="post">
							<input type="hidden" name="courseid" value="${courseid}" />
							<div class="form-group">
								<input type="text" class="form-control" placeholder="请输入学生学号" name="userid" style="margin-right: 10px;">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="请输入学生姓名" name="username" style="margin-right: 10px;">
							</div>
							<button type="submit" class="btn btn-primary">搜索</button>
						</form>
						<button onclick="exportTableToExcelCleaned('GradeTable', '课程信息')" class="btn btn-primary">导出 Excel</button>
					</div>
				</div>
					<table class="table table-bordered" id="GradeTable">
						<thead>
							<tr>
								<th>学号</th>
								<th>姓名</th>
								<th>分数</th>
								<th>登记成绩</th>
								<th>修改成绩</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${selectedCourseList}" var="item">
								<tr>
									<td>${item.studentCustom.userid}</td>
									<td>${item.studentCustom.username}</td>
									<c:if test="${!item.over}">
										<td>未打分</td>
										<td>
											<button class="btn btn-default btn-xs btn-info"
												onClick="location.href='${pageContext.request.contextPath}/teacher/mark?studentid=${item.studentid}&courseid=${item.courseid}'">打分</button>&nbsp&nbsp&nbsp
										</td>
									</c:if>
									<c:if test="${item.over}">
										<td>${item.mark}</td>
										<td>已打分</td>
										<td>
											<button class="btn btn-default btn-xs btn-info"
												onClick="location.href='${pageContext.request.contextPath}/teacher/mark?studentid=${item.studentid}&courseid=${item.courseid}'">修改</button>
										</td>
									</c:if>
								</tr>
							</c:forEach>
							<td>
								<button class="btn btn-default btn-xs btn-danger btn-primary"
									onClick="location.href='${pageContext.request.contextPath}/admin/removeStudent?id=${item.userid}'">删除名单</button>
								<!--弹出框-->
							</td>
						</tbody>
					</table>
					<div class="panel-footer">
						<c:if test="${pagingVO != null}">
							<nav style="text-align: center">
								<ul class="pagination">
									<li><a
										href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
									<li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
									<c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
										<li><a
											href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
										<li><a
											href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
										<li><a
											href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
										<li><a
											href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a></li>
									</c:if>
									<li><a
										href="${pageContext.request.contextPath}/student/showCourse?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
								</ul>
							</nav>
						</c:if>
					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
		<%--设置菜单中--%>
		$("#nav li:nth-child(1)").addClass("active")
        <c:if test="${pagingVO != null}">
        if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
            $(".pagination li:last-child").addClass("disabled")
        };

        if (${pagingVO.curentPageNo} == ${1}) {
            $(".pagination li:nth-child(1)").addClass("disabled")
        };
        </c:if>

        function confirmd() {
            var msg = "您真的确定要删除吗？！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }

        $("#sub").click(function () {
            $("#form1").submit();
        });

		function exportTableToExcelCleaned(tableId, filename = '课程信息') {
			const originalTable = document.getElementById(tableId);

			// 克隆原始表格 避免影响页面
			const clonedTable = originalTable.cloneNode(true);

			// 删除最后一行 {操作行}
			const rowCount = clonedTable.rows.length;
			if (rowCount > 0) {
				clonedTable.deleteRow(rowCount - 1);
			}

			// 删除每行最后两列 {状态信息列}
			for (let row of clonedTable.rows) {
				const cellCount = row.cells.length;
				if (cellCount >= 2) {
					row.deleteCell(cellCount - 1); // 删除最后一列
					row.deleteCell(cellCount - 2); // 删除倒数第二列
				}
			}

			// 导出
			const wb = XLSX.utils.table_to_book(clonedTable, {sheet: "Sheet1"});
			XLSX.writeFile(wb, filename + `.xlsx`);
		}
	</script>
</html>