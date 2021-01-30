# java-in-all


|***purorder***	 |***purorder节点下内容***	 | | | | |
|:-----	             |:----- 	           |:-----           |:-----	     |:-----	                 |:-----
|字段名	             |字段标题	           |字段类型          |能否为空	 |字段说明	                     |枚举取值范围
|vbillcode           |nc订单编号             |varchar(24)	 |否         | 	           	 	    
|storecode           |仓库                  |varchar(24)      |否         |                               |       
|orderdate           |订单日期               |string           |否         |yyyy-MM-dd HH\:mm\:ss            |    
|planarrvdate        |计划到货日期            |string           |是         |yyyy-MM-dd HH\:mm\:ss            |
|suppliercode        |供应商编码              |varchar(50)      |是         |
|suppliername        |供应商名称              |varchar(160)     |是         |           
|remark              |备注                   |varchar(1024)    |是         |
|purordergs          |子表明细                |Array<Purorderg> |是         |子表明细
