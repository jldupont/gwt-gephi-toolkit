#!/usr/bin/env python
"""
    Extracts the src files of the toolkit
     from the full jar of Gephi
    
    USAGE:  make.py [path/to/gephi-sources] dst-path
    
        where 'dst-path' is a path where to copy the required modules
    
    @author: Jean-Lou Dupont
"""
import argparse
import shutil
import glob
import os
import sys
import errno


### only these packages are really required
tk_pkgs=[
         "data"
         ,"dynamic"
         ,"filters"
         ,"graph"
         ,"layout"
         ,"preview"
         ,"project"
         ,"utils"
         ,"workspace"
         ]

def mkdir_p(path):
    try:
        os.makedirs(path)
    except OSError, exc:
        if exc.errno == errno.EEXIST:
            pass
        else: 
            raise
        
def cexec(cond, fnc, msg=None):
    if cond:
        fnc()
        if msg is not None:
            print msg

        
        
def pprint(x):
    print " %s" % x

def get_dirs(path):
    _files=glob.glob(os.path.join(path, "*"))
    _dirs=filter(os.path.isdir, _files)
    return _dirs
    

def filter_src(path):
    """ 
    Check if 'path' x contains a '$path/src/org/gephi' directory
    
    @param path: absolute path to check 
    """
    cpath=os.path.join(path, "src", "org", "gephi")
    return os.path.isdir(cpath)
    
def filter_interesting(liste):
    
    def in_liste(bname):
        #print " in_liste(%s) "% bname
        return bname in liste
    
    def f2(bnames):
        """
        Is any of the 'liste' element in 'bnames'?
        """
        filtered=filter(in_liste, bnames)
        return any(filtered)
    
    def f(path):
        """
        For each element in 'liste', determine if "$path/$el" is a directory
        """
        #print " f(%s)" % path
        cpath=os.path.join(path, "src", "org", "gephi")
        _dirs=get_dirs(cpath)
        bnames=map(os.path.basename, _dirs)
        return f2(bnames)
    
    return f

def copy_dirs(dirs, dst):
    
    def copy_one(src):
        copytree(src, dst)
        print "* copy src '%s' to dst '%s'" % (src, dst)
        
    map(copy_one, dirs)
    
def copytree(src, dst, symlinks=False, ignore=None):
    names = os.listdir(src)
    if ignore is not None:
        ignored_names = ignore(src, names)
    else:
        ignored_names = set()

    try:    os.makedirs(dst)
    except: pass
    
    errors = []
    for name in names:
        if name in ignored_names:
            continue
        srcname = os.path.join(src, name)
        dstname = os.path.join(dst, name)
        try:
            if symlinks and os.path.islink(srcname):
                linkto = os.readlink(srcname)
                os.symlink(linkto, dstname)
            elif os.path.isdir(srcname):
                copytree(srcname, dstname, symlinks, ignore)
            else:
                shutil.copy2(srcname, dstname)
            # XXX What about devices, sockets etc.?
        except (IOError, os.error), why:
            errors.append((srcname, dstname, str(why)))
        # catch the Error from the recursive copytree so that we can
        # continue with other files
        except shutil.Error, err:
            errors.extend(err.args[0])
    try:
        shutil.copystat(src, dst)
    except OSError, why:
        errors.extend((src, dst, str(why)))
    if errors:
        raise shutil.Error(errors)    

def expandenvvars(path):
    p1=os.path.expanduser(path)
    return os.path.expandvars(p1)

def main():
    parser = argparse.ArgumentParser(description='Process gephi toolkit files')
    
    parser.add_argument('-e', dest='execute', action='store_true', default=False, help="Execute")
    parser.add_argument('-i', dest='interesting', action='store_true', default=False, help="Display interesting directories")
    parser.add_argument('source_path', action="store")
    parser.add_argument('dest_path', action="store")
    
    params=parser.parse_args()
    
    execon=params.execute #shortcut
    emsg="ON" if params.execute else "OFF"
    print "> Execute: %s" % emsg
        
    source_path=expandenvvars(params.source_path)
    dest_path=expandenvvars(params.dest_path)
        
    ## all the files in path
    source_files=glob.glob(os.path.join(source_path, "*"))
    #map(pprint, source_files)
    
    ## but keep only the directories
    fdirs=filter(os.path.isdir, source_files)
    #map(pprint, fdirs)
    
    # Extract org.gephi.*  from each directory (if possible)
    gsrc_dirs=filter(filter_src, fdirs)
    #map(pprint, gsrc_dirs)
    print "> '%s' directories with gephi source code" % len(gsrc_dirs)
    
    #check if the required directories are present in the source_path
    isrc_dirs=filter(filter_interesting(tk_pkgs), gsrc_dirs)
    
    ci=len(isrc_dirs)
    ct=len(tk_pkgs)
    
    print "> '%s' directories found of interest for the toolkit" % ci
    
    if params.interesting:
        print "> Intesting directories:"
        map(pprint, isrc_dirs)

    cdirs=map(lambda x:os.path.join(x, "src"),isrc_dirs)

    # create target directory only if asked and not already created    
    #dst_path=os.path.join(params.dest_path, "org", "gephi")

    cexec(execon, lambda: mkdir_p(dest_path), "> Created dst directory: %s" % dest_path)

    cexec(execon, lambda: copy_dirs(cdirs,dest_path), "> Done")
    
    ## assume success
    return 0
    
    
sys.exit(main())
