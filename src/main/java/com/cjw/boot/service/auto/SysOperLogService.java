package com.cjw.boot.service.auto;


import com.cjw.boot.common.base.BaseService;
import com.cjw.boot.common.support.Convert;
import com.cjw.boot.mapper.auto.TsysOperLogMapper;
import com.cjw.boot.pojo.auto.TsysOperLog;
import com.cjw.boot.pojo.auto.TsysOperLogExample;
import com.cjw.boot.pojo.custom.Tablepar;
import com.cjw.boot.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOperLogService implements BaseService<TsysOperLog, TsysOperLogExample> {
	
	//文件mapper
	@Autowired
	private TsysOperLogMapper tsysOperLogMapper;
	
	/**
	 * 分页查询
	 * @param tablepar
	 * @param searchTxt
	 * @return
	 */
	 public PageInfo<TsysOperLog> list(Tablepar tablepar, String searchTxt){
	        TsysOperLogExample testExample=new TsysOperLogExample();
	        testExample.setOrderByClause("id desc");
	        if(searchTxt!=null&&!"".equals(searchTxt)){
	        	testExample.createCriteria().andTitleLike("%"+searchTxt+"%");
	        }

	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<TsysOperLog> list= tsysOperLogMapper.selectByExample(testExample);
	        PageInfo<TsysOperLog> pageInfo = new PageInfo<TsysOperLog>(list);
	        return  pageInfo;
	 }

	
	@Override
	public int deleteByPrimaryKey(String ids) {
		List<String> lista= Convert.toListStrArray(ids);
		TsysOperLogExample example=new TsysOperLogExample();
		example.createCriteria().andIdIn(lista);
		return tsysOperLogMapper.deleteByExample(example);
	}


	
	


	@Override
	public TsysOperLog selectByPrimaryKey(String id) {
		
		return tsysOperLogMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public int updateByPrimaryKeySelective(TsysOperLog record) {
		return tsysOperLogMapper.updateByPrimaryKeySelective(record);
	}
	


	
	@Override
	public int updateByExampleSelective(TsysOperLog record, TsysOperLogExample example) {
		
		return tsysOperLogMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(TsysOperLog record, TsysOperLogExample example) {
		
		return tsysOperLogMapper.updateByExample(record, example);
	}

	@Override
	public List<TsysOperLog> selectByExample(TsysOperLogExample example) {
		
		return tsysOperLogMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(TsysOperLogExample example) {
		
		return tsysOperLogMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(TsysOperLogExample example) {
		
		return tsysOperLogMapper.deleteByExample(example);
	}


	
	@Override
	public int insertSelective(TsysOperLog record) {
		record.setId(SnowflakeIdWorker.getUUID());
		return tsysOperLogMapper.insertSelective(record);
	}
	
}
