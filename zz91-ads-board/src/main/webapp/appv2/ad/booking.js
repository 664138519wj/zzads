Ext.namespace("com.zz91.ads.board.ad.booking");

Ext.define("BookingModel",{
	extend:"Ext.data.Model",
	fields:[
		{name:"id",mapping:"id"},
		{name:"positionId",mapping:"positionId"},
		{name:"crmId",mapping:"crmId"},
		{name:"account",mapping:"account"},
		{name:"keywords",mapping:"keywords"},
		{name:"email",mapping:"email"},
		{name:"gmt_booking",mapping:"gmtBooking"},
		{name:"remark",mapping:"remark"}
	]
});

Ext.define("ast.ast1949.admin.dataIndex.BaseGrid", {
	extend:"Ext.grid.Panel",
	initComponent:function(){
		
		var _store=Ext.create("Ext.data.Store",{
			model:"BookingModel",
			remoteSort:true,
			pageSize:Context.PAGE_SIZE,
			proxy:{
				type:"ajax",
				url:Context.ROOT+"/ad/ad/query.htm",
				simpleSortMode:true,
				reader: {
		            type: 'json',
		            root: 'records',
		            totalProperty:"totals"
		        }
			},
			autoLoad:false
		});
		
		var c={
		}
		
		Ext.applyIf(this,c);
		
		this.callParent();
	}
});